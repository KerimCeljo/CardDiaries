<?php
require_once dirname(__FILE__) . "/../config.php";
require_once dirname(__FILE__) . "/../DatabaseConnection.class.php";
/**
 * Main class for interacting with the database.
 *
 * Every other DAO class should inherit this class.
 *
 * @author Ahmed Becirevic
 */
class BaseDao
{
    private $table;

    public function __construct($table)
    {
        $this->table = $table;
    }

    public function beginTransaction()
    {
        DatabaseConnection::getInstance()->beginTransaction();
    }

    public function rollBack()
    {
        DatabaseConnection::getInstance()->rollBack();
    }

    public function commit()
    {
        DatabaseConnection::getInstance()->commit();
    }

    public function parse_order($order)
    {
        switch (mb_substr($order, 0, 1)) {
            case '-':
                $order_direction = "ASC";
                break;
            case '+':
                $order_direction = "DESC";
                break;
            default:
                throw new Exception("Invalid order format, character should be either + or -");
        }
        $order_column = trim(DatabaseConnection::getInstance()->quote(substr($order, 1)), "'");
        return [$order_column, $order_direction];
    }

    protected function insert($table, $entity)
    {
        $query = "INSERT INTO $table (";

        foreach ($entity as $column => $value) {
            $query .= "$column, ";
        }
        $query = substr($query, 0, -2) . ") VALUES (";

        foreach ($entity as $column => $value) {
            $query .= ":$column, ";
        }
        $query = substr($query, 0, -2) . ")";

        $stmt = DatabaseConnection::getInstance()->prepare($query);
        $stmt->execute($entity);         //prevent sql injection
        $entity["id"] = DatabaseConnection::getInstance()->lastInsertId();
        return $entity;
    }

    protected function execute_update($table, $id, $entity, $id_column = "id")
    {
        //generating automated query
        $query = "UPDATE $table SET ";

        foreach ($entity as $column => $value) {
            $query .= $column . " = :" . $column . ", ";
        }

        $query = substr($query, 0, -2);
        $query .= " WHERE $id_column = :id";

        //executing the query
        $stmt = DatabaseConnection::getInstance()->prepare($query);
        $entity['id'] = $id;
        $stmt->execute($entity);
    }

    protected function query($query, $params)
    {
        $stmt = DatabaseConnection::getInstance()->prepare($query);
        $stmt->execute($params);

        return $stmt->fetchAll(PDO::FETCH_ASSOC); // PDO::FETCH_ASSOC used to ensure no duplicate elements
    }

    protected function query_unique($query, $params)
    {
        $result = $this->query($query, $params);
        return reset($result); // reset - returns first element of array, checks if null etc.
    }

    public function add($entity)
    {
        return $this->insert($this->table, $entity);
    }

    public function update($id, $entity)
    {
        $this->execute_update($this->table, $id, $entity);
    }

    public function get_by_id($id)
    {
        return $this->query_unique("SELECT * FROM " . $this->table . " WHERE id = :id", ["id" => $id]);
    }

    public function get_all($offset = 0, $limit = 25, $order = "-id")
    {
        list($order_column, $order_direction) = $this->parse_order($order);
        return $this->query("SELECT * FROM " . $this->table . "
                            ORDER BY $order_column $order_direction 
                            LIMIT $limit OFFSET $offset", []);
    }
}
