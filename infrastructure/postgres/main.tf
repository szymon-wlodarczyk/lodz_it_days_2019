resource "azurerm_postgresql_server" "postgresql" {
  name                = "${var.postgresql_server_name}"
  location            = "${var.location}"
  resource_group_name = "${var.resource_group_name}"

  sku {
    name     = "B_Gen5_2"
    capacity = 2
    tier     = "Basic"
    family   = "Gen5"
  }

  storage_profile {
    storage_mb            = 61440
    backup_retention_days = 7
    geo_redundant_backup  = "Disabled"
  }

  administrator_login          = "${var.postgresql_server_login}"
  administrator_login_password = "${var.postgresql_server_password}"
  version                      = "${var.postgresql_version}"
  ssl_enforcement              = "Enabled"

}

resource "azurerm_postgresql_database" "db" {
  name                = "${var.postgresql_database_name}"
  resource_group_name = "${var.resource_group_name}"
  server_name         = "${azurerm_postgresql_server.postgresql.name}"
  charset             = "UTF8"
  collation           = "English_United States.1252"
}

resource "azurerm_postgresql_firewall_rule" "azure_services_access" {
  name                = "office"
  resource_group_name = "${var.resource_group_name}"
  server_name         = "${azurerm_postgresql_server.postgresql.name}"
  start_ip_address    = "0.0.0.0"
  end_ip_address      = "0.0.0.0"
}
