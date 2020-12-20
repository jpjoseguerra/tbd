# Proyecto Taller de Base de Datos
## Aplicación de Voluntariado

### Teconlogías
Las siguientes tecnologías fueron utilizadas:
* [Spring] - Back end framework
* [Vue.js] - Open source MVC framework para front end
* [PostgreSQL] - Sistema de manejo de base de datos relacional

### Requerimientos

Este proyecto requiere de las siguientes dependencias para su funcionamiento:

* [Java 11] - Base para Spring 
* [node.js] - Entorno de ejecución open-source basado en JavaScript 
* [Node Packet Manager] - Manejador de paquetes para node
* [Gradle] - Herramienta para realizar el build del proyecto
* [Psycopg] - Adaptador de base de datos PostgreSQL para Python3

## Instalación
Descargar los contenidos del repositorio en un directorio a elección con el siguiente comando:

```sh
$ git clone https://github.com/Rodolfato/TBD.git
```
### Base de datos

Debe ingresar el usuario y contraseña de la instancia de la base de datos PostgreSQL en los siguientes archivos encontrados dentro de la carpeta *Backup*:

Dentro de *fake_data.py* y *load_data.py*:
![alt text](https://imgur.com/jKDmWMo.png)
Y el nombre de la base de datos en la parte final de ambos archivos:
![alt text](https://imgur.com/gZmXm1c.png)
Reemplazando *user*, *password*, *port* y *dbName_f* según corresponda.

Una vez realizado esto es necesario cargar el backup de la base de datos utilizando PgAdmin4:
![alt text](https://imgur.com/FU9zbCz.png)
Y cargar el archivo llamado: *voluntariado_backup_fk_actions*
![alt text](https://imgur.com/MMZXHpz.png)

Una vez cargado el backup a la base de datos, es necesario rellenarla de datos de prueba utilizando el programa llamado *fake_data.py*, ejecutando el siguiente comando dentro de la carpeta *Backup*:

```sh
$ python fake_data.py
```

Si se desea borrar datos guardados con anterioridad, se debe ejecutar el programa *load_data.py*, de la misma forma:
```sh
$ python load_data.py
```
### Back end
Primero es necesario editar el usuario y contraseña de la base de datos en el archivo *application.properties* encontrado en *.../voluntariado-spring/src/main/resources*, cambiando los datos según corresponda:
![alt text](https://imgur.com/0OKPujz.png)

Para hacer el *build* y correr el back end es necesario navegar al siguiente directorio *.../voluntariado-spring/* y ejecutar el siguiente comando en el terminal:
```sh
$ gradle build
```
Seguido de:
```sh
$ gradle bootRun
```

Ahora es posible ingresar a través de un navegador de internet a la dirección *localhost:3000* y recibir un mensaje de *Hello World*:
![alt text](https://imgur.com/2E9nplh.png)
### Front end
Una vez iniciado el back en de la aplicación es necesario, navegar al directorio *.../ejemplo-vue/* y en un terminal ejecutar el siguiente comando:
```sh
$ npm install
```
Seguido de
```sh
$ npm run serve
```
Y al aparecer este mensaje en su terminal:

![alt text](https://imgur.com/qXB0yQs.png)

Es posible entrar a la aplicación siguiendo la ruta señalada.



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [Java 11]: <https://openjdk.java.net/projects/jdk/11/>
   [Node Packet Manager]: <https://www.npmjs.com/>
   [Gradle]: <https://gradle.org/>
   [Psycopg]: <https://www.psycopg.org/>
   [Spring]: <https://spring.io/>
   [Vue.js]: <https://vuejs.org/>
   [node.js]: <http://nodejs.org>
   [PostgreSQL]: <https://www.postgresql.org/>

