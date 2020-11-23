# *Технологии разработки программного обеспечения*
## *Лабораторная работа №2: создание кластера Kubernetes и деплой приложения*
### *Черезов Михаил Сергеевич ЗМАС2031*
### *Цель лабораторной работы:*

Знакомство с кластерной архитектурой на примере Kubernetes, а также деплоем приложения в кластер.
Развернуть кластер Kubernetes на локальной рабочей станции посредством MiniKube.

### *Инструкция по использованию*
Minikube — это инструмент, позволяющий легко запускать Kubernetes на локальной машине

Kubernetes оркестратор контеййнеров

---

 Чтобы применить манифесты deployment.yaml и service.yaml, необходимо выполнить  команду:
 
 
  `kubectl apply -f .`

## service.yaml

```
apiVersion: v1
kind: Service
metadata:
  name: my-service
spec:
  type: NodePort
  ports:
    - nodePort: 31317
      port: 8080
      protocol: TCP
      targetPort: 8080
  selector:
    app: simpleapi 
```

## deployment.yaml

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-deployment
spec:
  replicas: 2
  selector:
    matchLabels:
      app: simpleapi
  strategy:
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 1
    type: RollingUpdate
  template:
    metadata:
      labels:
        app: simpleapi
    spec:
      containers:
        - image: kaggle
          imagePullPolicy: Never
          name: simpleapi
          ports:
            - containerPort: 8080
      hostAliases:
        - ip: "192.168.49.1"
          hostnames:
          - postgres.local        
          
```

## Проверяем  количество подов с помощью команды:

   `kubectl get pods`
    
 [Скриншты вывода команды консоли](https://github.com/muxache/deliveryservice/kuber/telegram-cloud-photo-size-2-5291920830395166986-y)

## Осмотр подов в графическом интерфейсе:

[Скриншоты графического интерфейса с подами](https://github.com/muxache/deliveryservice/kuber/telegram-cloud-photo-size-2-5291920830395166988-y)

## Обращаюсь к ендпоинту, где отображается hostname:

https://localhost:31317/api/hostname
