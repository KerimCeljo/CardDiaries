<?php

require_once __DIR__ . "/config.php";

class DatabaseConnection
{
    private static $conn;

    /**
     * Disable instantiation.
     */
    private function __construct()
    {
    }

    /**
     * Create or retrieve the instance of our database client.
     *
     * @return Database
     */
    public static function getInstance()
    {
        if (is_null(static::$conn)) {
            try {
                static::$conn = new PDO("mysql:host=" . Config::DB_HOST() . ";port=" . Config::DB_PORT() . ";dbname=" . Config::DB_SCHEME(), Config::DB_USERNAME(), Config::DB_PASSWORD());
                static::$conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                date_default_timezone_set("Europe/Sarajevo");
                // $this->connection->setAttribute(PDO::ATTR_AUTOCOMMIT, 0);
            } catch (PDOException $e) {
                echo "Connection failed: " . $e->getMessage();
            }
        }

        return static::$conn;
    }

    /**
     * Disable the cloning of this class.
     *
     * @return void
     */
    final public function __clone()
    {
        throw new Exception('Feature disabled.');
    }

    /**
     * Disable the wakeup of this class.
     *
     * @return void
     */
    final public function __wakeup()
    {
        throw new Exception('Feature disabled.');
    }
}
