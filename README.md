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
mvn clean install
docker build --platform=linux/arm64 -t processor-app-img .
docker tag processor-app-img:latest ranadaud89/processor-ms-app:latest
docker login
docker push ranadaud89/processor-ms-app:latest

kubectl apply -f deployment/processor-service.yaml
kubectl apply -f deployment/processor-deployment.yaml
```

### Deploy Loader service:

```
mvn clean install
docker build -t loader-app-img .
docker tag loader-app-img:latest ruqayakhalil/loader-ms-app:latest
docker login
docker push ruqayakhalil/loader-ms-app:latest

kubectl apply -f deployment/loaderdb-service.yaml
kubectl apply -f deployment/loaderdb-statefulset.yaml

kubectl apply -f deployment/loader-service.yaml
kubectl apply -f deployment/loader-deployment.yaml
```

### Deploy Evaluation service:

```
mvn clean install
docker build -t evaluation-app-img .
docker tag evaluation-app-img:latest ruqayakhalil/evaluation-ms-app:latest
docker login
docker push ruqayakhalil/evaluation-ms-app:latest

kubectl apply -f deployment/evaluation-service.yaml
kubectl apply -f deployment/evaluation-deployment.yaml
```

### Deploy MailNotification service:

```
mvn clean install
docker build -t email-ns-app-img .
docker tag email-ns-app-img:latest ruqayakhalil/email-ns-ms-app:latest
docker login
docker push ruqayakhalil/email-ns-ms-app:latest

kubectl apply -f deployment/email-service.yaml
kubectl apply -f deployment/email-deployment.yaml
```

### Deploy SMSNotification service:

```
mvn clean insall
docker build -t sms-ns-app-img .
docker tag sms-ns-app-img:latest ruqayakhalil/sms-ns-ms-app:latest
docker login
docker push ruqayakhalil/sms-ns-ms-app:latest

kubectl apply -f deployment/sms-service.yaml
kubectl apply -f deployment/sms-deployment.yaml
```

### Deploy Logger service:

```
mvn clean insall
docker build --platform=linux/arm64 -t logger-app-img .
docker tag logger-app-img:latest ranadaud89/logger-ms-app:latest
docker login
docker push ranadaud89/logger-ms-app:latest

kubectl apply -f deployment/mongo-pv.yaml
kubectl apply -f deployment/mongo-service.yaml
kubectl apply -f deployment/mongo-deployment.yaml

kubectl apply -f deployment/logger-service.yaml
kubectl apply -f deployment/logger-deployment.yaml
```
>>>>>>> 765d859 (Expose trigger manual scan + fix for job)
