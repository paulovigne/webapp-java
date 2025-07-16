# Etapa 1: build com Maven
FROM maven:3.9.6 AS build
WORKDIR /app

# Copia arquivos do projeto
COPY pom.xml .
COPY src ./src

# Faz o build do projeto
RUN mvn clean package -DskipTests

# Etapa 2: imagem final minimalista
FROM openjdk:17-alpine AS runtime

ENV APPUSER springuser
ENV UIDGID 1001
ENV APPPORT 8080

WORKDIR /app

# Cria um usuário não-root
RUN addgroup --gid ${UIDGID} ${APPUSER} \
 && adduser -S ${APPUSER} -u ${UIDGID} -g ${UIDGID}

# Copia o JAR da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Define usuário
USER ${APPUSER}

# Expõe a porta
EXPOSE ${APPPORT}

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]
