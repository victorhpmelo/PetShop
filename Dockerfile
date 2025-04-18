FROM maven:3.9.9-amazoncorretto-17

ENV INSTALL_PATH /PetShop

RUN mkdir $INSTALL_PATH

WORKDIR $INSTALL_PATH

COPY . .

