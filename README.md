# mst-alert-hub-system

## Kubernetes Deployment

### Deploy kafka:
```
kubectl apply -f ./deployment/kafka.yml
kubectl apply -f ./deployment/zookeeper.yml
```

### Deploy Action service:

```
mvn clean install
docker build --platform=linux/arm64 -t action-app-img .
docker tag action-app-img:latest ranadaud89/action-ms-app:latest
docker login
docker push ranadaud89/action-ms-app:latest

kubectl apply -f deployment/mysql-service.yaml
kubectl apply -f deployment/mysql-statefulset.yaml

kubectl apply -f deployment/action-service.yaml
kubectl apply -f deployment/action-deployment.yaml
```

### Deploy Metric service:

```
mvn clean insall
docker build --platform=linux/arm64 -t metric-app-img .
docker tag metric-app-img:latest ranadaud89/metric-ms-app:latest
docker login
docker push ranadaud89/metric-ms-app:latest

kubectl apply -f deployment/mysql-service.yaml
kubectl apply -f deployment/mysql-statefulset.yaml

kubectl apply -f deployment/metric-service.yaml
kubectl apply -f deployment/metric-deployment.yaml
```

### Deploy Processor service:

```
mvn clean insall
docker build --platform=linux/arm64 -t processor-app-img .
docker tag processor-app-img:latest ranadaud89/processor-ms-app:latest
docker login
docker push ranadaud89/processor-ms-app:latest

kubectl apply -f deployment/processor-service.yaml
kubectl apply -f deployment/processor-deployment.yaml
```