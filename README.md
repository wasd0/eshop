# eshop

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=wasd0_eshop&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=wasd0_eshop)

## Interaction of services

```mermaid
sequenceDiagram
    actor customer
    participant order_s as order <br> microservice
    participant product_s as product <br> microservice
    participant customer_s as customer <br> microservice
    customer ->> order_s: /post orders
    order_s ->> product_s: order created pending
    product_s -->> customer_s: product(s) are reserved
    customer_s -->> order_s: money reserved
    order_s -->> customer: order approved
```
