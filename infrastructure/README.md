# Getting Started
Terraform code which is responsible for setup Azure resources:
* IoT Hub,
* Event Hub,
* Storage account,
* SQL database server for PostgreSQL,
* Azure Container Service.

**Please note that Azure Container Service is marked as Deprecated and will be disabled in 20 Jan 2020.**

### Prerequisite
* Terraform 0.11.11
* Azure CLI 2.0.76

### Terraform commands
Installs provider and initializes modules
```terraform init```

Generates an execution plan
```terraform plan -var-file="env-vars.tfvars"```

Builds or changes infrastructure according to Terraform configuration
```terraform apply -var-file="env-vars.tfvars"```

