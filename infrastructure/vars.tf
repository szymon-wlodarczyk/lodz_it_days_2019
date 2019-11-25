variable "service_principal_app_id" {}

variable "service_principal_password" {}

variable "service_principal_tenant" {}

variable "location" {
  description = "Azure Resource Group location"
}
variable "postgresql_server_login" {
  description = "PostgreSQL server login name"
}

variable "postgresql_server_password" {
  description = "PostgreSQL server user password"
}
