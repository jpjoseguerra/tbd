# tbd
MANUAL DE USUARIO
Para poder ejecutar la aplicación, se deben tener instalados los siguientes programas:
•	Postgres 13
•	Java 11
•	Visual studio
•	Pg admin
•	Node.js
•	Git 
•	Python 3
Pasos a seguir
Debemos clonar el repositorio, en la consola de comandos escribimos lo siguiente
git clone https://github.com/jpjoseguerra/tbd.git
Luego, debemos tener instalado pg admin, desde pg admin creamos una base de datos con usuario y contraseña postgres
Una vez hecho esto, debemos abrir pg admin y hacer clic derecho en la base de datos postgres y elegir restore, luego elegimos el archivo que esta contenido en el repositorio clonado dentro de la carpeta backup con nombre backup_entrega2,
 
 
Gracias a este paso se habrán creado las tablas a usar, luego de esto, en la misma carpeta de backup, abrimos el archivo fake_data.py, el cual se ejecutara y finalizara de manera automática, este script rellenara los datos en la base de datos
Luego de esto, debemos  abrir la carpeta voluntariado-spring desde visual studio, buscamos y descargamos la extensión gradle, con esta, hacemos clic en build
 
Una vez que termine el proceso de build hacemos clic en application y luego bootrun
 
Con esto, estaría ya abierto y funcionando el backend de la aplicación, para esto podemos entrar a localhost:3000 a lo cual le podemos agregar alguna ruta para ocupar un servicio por ejemplo:
http://localhost:3000//tareas


Para la parte del front, nos dirigimos a la carpeta ejemplo-vue, desde esa carpeta abrimos la consola de comando o sino, desde la consola de comandos llegamos a la ruta anteponiendo a los nombres de las carpetas cd, por ejemplo: “cd desktop” enter, “cd tbd”y asi, una vez dentro escribimos los siguientes comandos:
npm install
npm install --save leaflet vue2-leaflet  
(o el comando que nos solicite luego de hacer run serve y si es que nos muestra un error)
npm run serve

 
Accediendo a la dirección especificada nos mostrara la página principal del front, luego de esto podemos seguir probando la aplicación, estando conectados el back y el front
