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
![admineditasos](https://github.com/CodeURJC-DAW-2022-23/webapp2/blob/Documentos/Fase2/screenshot/editarasos.png?raw=true)
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

### Participación de miembros

**Daniel Gallardo de Eugenio:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

**Andrea Cardenal Millán:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	| [home.html](src/main/resources/templates/home.html) |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

**Rubén González de Pablo:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

**Fiorella Victoria Hernandez Mejía:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

**Daniel Pérez Moraleda:**

- Tareas realizadas: 

- 5 commits más significativos: 

| Commit	| Descripcion	| Link |
| --- | :---: | :---: |
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |

- 5 ficheros que más ha participado: 

| Numero	| Fichero	|
| --- | :---: | 
| 1	| 	|  |
| 2	| 	|  |
| 3 | 	|  |
|	4 |  |  |
| 5	|  |  |
