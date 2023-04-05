# sismepe-spring-backend
Backend do Sistema SISMEPE

# Maven
## Gerando o .JAR (package)

Base directory: ${project_loc}

Goals: package

# Execução
```
java -jar sismepe-spring-backend-[versão].jar
```

# Docker
Para gerar a imagem do container, esteja no diretório do projeto e execute:
```
docker build -t <nome da tag da imagem> .
```
Exemplo:
```
docker build -t sismepe-spring/v1 .
```

Para subir o container, é preciso ter uma imagem já construída. Então, basta executar o comando:
```
docker run -d -p <porta que vai escutar as conexões>:<porta da aplicação> <tag da imagem>
```
Exemplo:
```
docker run -d -p 8084:8098 sismepe-spring/v1
```