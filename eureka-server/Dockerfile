# The purpose is to package the api-config server service as a deploy unit.

FROM java:8
MAINTAINER "Huang Yan"

# Prepare work directories
ENV APP_DIR  /opt/apps

RUN mkdir -p $APP_DIR

WORKDIR $APP_DIR
COPY build/libs/eureka-server-1.0-SNAPSHOT.jar $APP_DIR
COPY startup.sh $APP_DIR
RUN chmod u+x ./startup.sh
HEALTHCHECK CMD wget -qO-  http://localhost:8001/health || exit 1
EXPOSE 8001
CMD ["./startup.sh"]