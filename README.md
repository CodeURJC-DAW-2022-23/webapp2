# webapp2

## Fase 0:

El grupo 2 desarrollará la aplicación web "Plataforma Asociaciones URJC". Esta consistirá en una página web donde se podrá buscar información sobre los distintos eventos de la URJC.

EL equipo se encuentra compuesto por:

| Nombre	| Email	| Github |
| --- | :---: | :---: |
| Daniel Gallardo de Eugenio	| d.gallardo.2020@alumnos.urjc.es	| DanielGallardo00 |
| Andrea Cardenal Millán	| a.cardenal.2020@alumnos.urjc.es	| AndreaCardenalMillan |
| Rubén González de Pablo | r.gonzalezd.2020@alumnos.urjc.es	| RudberGames |
|	Fiorella Victoria Hernandez Mejía | fv.hernandez.2019@alumnos.urjc.es | fiorellaV |
| Daniel Pérez Moraleda	| d.perezm.2020@alumnos.urjc.es | Danielus99 |

### Entidades

* usuario
* evento
* asociación
* campus
* comentario

### Usuarios

* base no registrado (consultar información)
* base registrado (consultar información y apuntarse a eventos)
* asociación (maneja eventos propios)
* admin (maneja eventos y usuario)

### Estructura

#### Funcionalidades usuario base

Logearse, registrarse, comentar eventos finalizados, y buscar, filtrar y apuntarse a eventos (este último solo registrados)

#### Funcionalidades usuario asociación

Agregar, modificar y eliminar eventos de su asociación

#### Funcionalidades usuario admin

Gestionar evento de todas las asociaciones y cuenta de usuario

### Imágenes

La página web contendrá un conjunto de imágenes propias correspondiente a su identidad corporativa.

Además, los usuarios pertenecientes a asociaciones y administradores podrán añadir o cambiar las imágenes representativas en sus respectivos eventos.

### Gráficos

Se utilizará un complemento web tipo Google Calendar donde poder mostrar los eventos.

### Tecnología complementaria

La aplicación web será capaz de gestionar su propia cuenta de correo, que tendrá como principal función enviar correos a los usuarios registrados que se apunten a un evento.

### Algoritmo o consulta avanzada

Se creará un algoritmo capaz de buscar y filtrar adecuadamente los eventos.

## Fase 1

![landing](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/home.PNG?raw=true)
![landing](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/home2.PNG?raw=true)
La *landing page* (o "página de aterrizaje") consiste en un menú con una barra de búsqueda con opciones de filtrado y un botón que permite el acceso a los administradores y a los usuarios de las asociaciones. En la parte inferior de la barra de búsqueda, aparecen distintos eventos según lo filtado por la barra de búsqueda.

![detalles](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/detalles.PNG?raw=true)
La página de detalle de eventos muestra información adicional de un evento. Consiste de un texto descriptivo, lugar y horario. También cuenta con un botón de seguir evento para poder recibir avisos sobre este.

![login](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/loginAsociaciones.PNG?raw=true)
La página de inicio de sesión de un usuario base consta de los campos de nombre de usuario y contraseña, demás de un botón para continuar.

**Captura login admin aquí**
La página de login de un usuario de asociación/administración consta de los campos de nombre de usuario y contraseña, demás de un botón para continuar.

![login](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/eventform.PNG?raw=true)
El formulario de asociación permite configurar párámetros relacionados con la creación, modificación y borrado de eventos de la asociación.

![login](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase1/screenshot/eventform.PNG?raw=true)
El formulario de administración permite configurar párámetros relacionados con la creación, modificación y borrado de todos los eventos. Además, también puede cambiar la configuración del resto de usuarios e incluso borrar usuarios.

## Fase 2: 

### Navegación

**Home:**

![landing](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/home.png?raw=true)
La aplicación tiene un menú inicial común para los tres tipos de usuarios (administradores, asociaciones o usuarios base). 

**Registro:**

![register](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/registro.png?raw=true)
Esta pantalla permitirá a los usuarios registrarse y dependiendo de su rol tener unos privilegios u otros.

Registro usuario BASE: 
Este tipo de usuario sólo requiere un correo electrónico, nombre de usuario y contraseña correctos para poder registrarse.

Registro usuario ASOCIACIÓN: 
Este tipo de usuario va a necesitar la validación de un administrador para que su registro quede completado y confirmado.

**Login:**

![login](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/login.png?raw=true)
Una vez registrado, el usuario deberá introducir unas credenciales válidas, su nombre de usuario y contraseña. De lo contrario se mostrará un mensaje de error.

**Más información sobre un evento:**

![detallesnuevo](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/detalles.PNG?raw=true)
![fav](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/favoritos.PNG?raw=true)
![comments](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/comments.PNG?raw=true)

La página de detalles de eventos muestra información adicional de un evento. Consiste de un texto descriptivo, lugar y horario. También cuenta con un botón de seguir evento para poder recibir avisos sobre este. Además, se ha añadido una sección de comentarios donde los usuarios podrán opinar. También se ha implementado dar like a otros comentarios, dislike a un evento o poner en tu lista de favoritos el evento que elijas.

**Menú de usuario base:**

Ver favoritos: 
![favuser](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/userfavoritos.png?raw=true)
Aquí el usuario podrá consultar su listado de eventos favoritos.

Editar su información personal:
![edituser](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/baseeditarcuenta.png?raw=true)
El usuario podrá modificar sus datos personales.

**Menú de asociación:**

Crear eventos: 
![asocreatevent](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/asocrearevento.png?raw=true)
En esta vista se podrán crear eventos a través de un formulario.

Gestionar eventos: 
![asoeditevent](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/asoeditareventos.png?raw=true)
Aquí el usuario asociación podrá modificar o eliminar los datos que precisen.

Gestionar asociación:
![asoeditaso](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/asoeditaraso.png?raw=true)
En esta vista se podrá modificar los datos propios de su asociación.

**Menú de administrador:**

Gestionar usuarios: 
![admineditusers](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/editarusuarios.png?raw=true)
Aquí el administrador podrá consultar, modificar o eliminar cualquier usuario de la plataforma.

Gestionar eventos: 
![admineditevents](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/admineditarevento.png?raw=true)
En esta vista el administrador podrá consultar, modificar o eliminar cualquier evento de la plataforma.

Gestionar asociaciones: 
![admineditasos](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/admineditasos.png?raw=true)
El administrador podrá consultar, modificar o eliminar cualquier asociación de la plataforma.

Email: 
![email](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/email.png?raw=true)
Esta es la tecnología que hemos decidido implementar consiste en el uso de un email para validar las cuentas del los usuarios dueños de asociaciones. Esta validación la tiene que hacer obligatoriamente un usuario administrador. En caso de que se rechace la validación la cuenta no se creará. Por el contrario, si se acepta la creación del usuario asociación, este ya podrá crear su asociación.

### Instrucciones de ejecución

- Acceder al repositorio mediante este enlace: CodeURJC-DAW-2022-23 /webapp2
- Descargar el zip del proyecto
![git](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/git.png?raw=true)

- Nosotros hemos utilizado VisualStudio para trabajar instalando las correspondientes librerías de Spring
![extensiones](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/sb.png?raw=true)

- Nuestra versión Maven 4.0.0, SpringBoot 2.7.8, MySQL y Java 11.
![versiones](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/versiones.png?raw=true)

- Para consultar los datos hemos usado WorkBench 8.0 CE. 
![wb](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/workbench.png?raw=true)

### Diagrama con las entidades de la base de datos
![basededatos](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/bd.png?raw=true)

### Diagrama de clases y templates
![diagramaclases](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/diagramaclases.png?raw=true)

### Participación de miembros

**Daniel Gallardo de Eugenio:**

- Tareas realizadas: Mi trabajo ha consistido en crear las vistas y la lógica del registro y el login de la aplicación, además de implementar la comprobación por correo electrónico de las cuentas de asociación. También me he encargado de la función de seguridad. En la vista de moreinfo he hecho la lógica de agregar eventos favoritos. Por último, también he implementado la lógica del administrador.

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| implementacion de la comprobacion de correo electrónico	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/1ed5c0a3e8089e4569d4763e5ef5d7d49139e293 |
| 2	| agregar eventos favoritos de un usuario	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/73477c32835c9550bddb94f23deda72e31be05ba |
| 3 | login y registro	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/5d3cc99c14f99a7da64c5135e1a416aff2004bbf |
|	4 | funcion de seguridad | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/5d3cc99c14f99a7da64c5135e1a416aff2004bbf |
| 5	| aceptar y rechazar creacion de una cuenta | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/5d3cc99c14f99a7da64c5135e1a416aff2004bbf |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| Login.html |
| 2	| EmailRequirementController.java |
| 3 | FavoritesController.java |
|	4 | RegisterController.java |
| 5	| HomeController.java |

**Andrea Cardenal Millán:**

- Tareas realizadas: He participado en la creacion de los modelos, repositorios y controladores relativos con los eventos y los usuarios. También todo lo relacionado con los formularios de crear y editar eventos y usuarios. La vista de mas información acerca de los eventos, su sección de comentarios y el ajax de la vista home en relación con cargar más eventos.

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Añadir seccion de comentarios	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/02ecb12bc0f58b4f1ebf35c2e78febdb9bd0279d|
| 2	| Vista del menu de un usuario asociacion	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/5fc6753a2ebee6b986c6ad98194426aa392284e8 |
| 3 | Formulario para crear asociacion	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/77e9077b6f1062929573ee98fb61f0786f857700 |
|	4 | Vista dedicada a mas informacion sobre un evento | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/c033e7794add513e29e25ed55235a0b78f1c5008 |
| 5	| Ajax relacionado con cargar mas eventos | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/746b0bafa1cbd2352cb96d198c71d9fb49985fbe |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| CommentController.java |
| 2	| Editevent.html |
| 3 | Moreinfo.html |
|	4 | Home.html |
| 5	| UserController.java |

**Rubén González de Pablo:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Añadida la versión preliminar de los filtros	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/02ecb12bc0f58b4f1ebf35c2e78febdb9bd0279d |
| 2	| Agrupación de los datos de los filtros para en envio	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/d19f409ddcddc35db47663f9215e0a9015507f7d |
| 3 | Filtros funcionales con Query dinamica	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/fb65dc0cd7ec5e54abf657a918936c0d1a70d6a3 |
|	4 | Iniciada la implementación de AJAX con paginación en la Query | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/38a536cc18f37c0f72c25ed028265217e5cb7f46 |
| 5	| Funcionamiento del envio de los datos de los filtros en Firefox | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/25546e24943e5fe7318d894a5b53cad9271f354c |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	HomeController.java  |
| 2	| 	Event.java  |
| 3 | 	EventService.java  |
|	4 |  CustomEventRepositoryImpl.java  |
| 5	|  Home.html  |

**Fiorella Victoria Hernandez Mejía:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Crear eventos	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/74361c93b69a3d878d114899a52d4aef100eca7c |
| 2	| Edicion de los eventos desde ASO 	| https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/7f10c2e0c8d590ddda4db76ec3a4a396cfd89bd1 |
| 3 | Eliminar evento como asociación | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/532a9cb6afd4ea851d1f44f61436baf97e5d82a6 |
|	4 | Remodelación de la edición de eventos del admin | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/eee106b63ef04e612fc317742968e594fdc2717b |
| 5	| Edición de la asociacion | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/068fb30fc7dd0062b315cd9a42194626b6191f75 |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| EditEventAssoController.java  |
| 2	| NewEventController.java  |
| 3 | createEvent.html |
|	4 | eventManager.html  |
| 5	| EditEventAssoController.java |

**Daniel Pérez Moraleda:**

- Tareas realizadas: 

Ha cubierto varios aspectos del desarrollo de la aplicación, siendo los más destacados los relacionados con el desarrollo de las clases "evento", "asociación" y la implementación de MySQL.

En el desarrollo de la clase "evento" ha implementado el sistema de puntuaje tanto para los comentarios como para los eventos, llegando a implementar una barra de "me gusta" y "no me gusta" que funciona de forma similar a la de sitios como Youtube. Además, mejoró el sistema de referencias entre las clases "asociación" y "evento", haciendo que la clase evento pueda acceder en todo momento al objeto asociación al que pertenece con tal de tener su nombre siempre actualizado.

En la clase "asociación", se ha encargado de gestionar los formularios de modificación y eliminación de asociaciones desde el menú de los distintos usuarios.

Por último, ha sido el principal responsable de implementar el servidor MySQL tanto en local (usando las herramientas oficiales de MySQL) como en la nube (con CleverCloud).

- 5 commits más significativos: 

| Commit | Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Principal implementación de la relación directa entre las clases "evento" y "asociación". | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/ade271c2f431b9546f164eee1c766eb02d3e2368 |
| 2	| Se implementa el sistema de "me gusta" en los comentarios. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/c72fae0dc3f9ae8bc30bd6ef1f7fd40564732ac3 |
| 3 | Se implementa la barra de "me gusta" en eventos junto con su sistema funcional. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/b849e9f0359c88aa0becfd077efae9a6015983f4 |
| 4 | Commit principal en la gestión de una asociación por parte del administrador. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/b0df8d86fc11290823e384611d14099e51764c9e |
| 5	| Commit principal en la gestión de una asociación por parte del usuario asociación. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/23e34faab57440016ca7a6cbc28b6b4c6f1ce6fb |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| AdminUserController.java	|
| 2	| EventDetailsController.java |
| 3 | AsociationUserController.java	|  
|	4 | Event.java |  
| 5	| myAso.html | 

## Fase 3: 

### Diagrama de clases y templates
![diagramaclases](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/main/AplicacionWEB/screenshot/DiagramaClasesFase3.png?raw=true)

### Participación de miembros

**Daniel Pérez Moraleda:**

- Tareas realizadas: 

Ha implementado el sistema de seguridad y login para API rest, además de crear el controlador API REST para el usuario.

- 5 commits más significativos: 

| Commit | Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Implementación de un body para el cambio de contraseñas. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/4e052cd94836df1735a1b9a0dd8dff1871fdd57f |
| 2	| Añadir y quitar favoritos de un usuario. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/78ac97c7b089d583e295fa2c75544cfe6b4c32c1 |
| 3 | Implementación de las seguridad para API REST + login. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/01f005dbe299af72443cec443285b4b5a29d1b2e |
| 4 | Modificación de usuarios desde API REST. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/774a7fd6515311a5adb21e88fbc2c0264da9de32 |
| 5	| Se añaden varios métodos al controlador de usuario de la API REST. | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/32a4a66e1999affca2c0dbdb0c5e0ea1a25bcc23 |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| ChangePassword.java	|
| 2	| UserRestController.java |
| 3 | LoginRestController.java	|  
| 4 | RestSecurityConfig.java |  
| 5	| LoginRequest.java | 

** Fiorella Victoria Hernández Mejía

- Tareas realizadas:

Documentación y API REST ligado a las fucionalidades asociación 

- 5 commits más significativos: 

| Commit | Descripcion	| Link |
| --- | :---: | :---: |
| 1	| Añadir editar asociacion a la API REST | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/04bade9c4e7964b5f5797a6db5d321ded979bcfb |
| 2	| Añadir consultar asociacion a la API REST | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/2c1161b0a7371848de9466522e963bfe5e6cc595 |
| 3 | Añadidas funcionalidades de asociación | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/a66f564ecbea5caaf526f6085e1c177de6640a9e |
| 4 | Añadir documentación de la clase AssoRestController | https://github.com/CodeURJC-DAW-2022-23/webapp2/commit/d93ac92e98b93e9da6179dd376d089f7aad0a2a6 |
