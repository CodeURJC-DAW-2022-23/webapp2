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

**Captura landing aquí**
La *landing page* (o "página de aterrizaje") consiste en un menú con una barra de búsqueda con opciones de filtrado y un botón que permite el acceso a los administradores y a los usuarios de las asociaciones. En la parte inferior de la barra de búsqueda, aparecen distintos eventos según lo filtado por la barra de búsqueda.

**Captura detalle evento aquí**
La página de detalle de eventos muestra información adicional de un evento. Consiste de un texto descriptivo, lugar y horario. También cuenta con un botón de seguir evento para poder recibir avisos sobre este.

**Captura login aquí**
La página de login de un usuario base consta de los campos de nombre de usuario y contraseña, demás de un botón para continuar.

**Captura login admin aquí**
La página de login de un usuario de asociación/administración consta de los campos de nombre de usuario y contraseña, demás de un botón para continuar.

**Captura formulario asociación aquí**
El formulario de asociación permite configurar párámetros relacionados con la creación, modificación y borrado de eventos de la asociación.

**Captura formulario admin aquí**
El formulario de administración permite configurar párámetros relacionados con la creación, modificación y borrado de todos los eventos. Además, también puede cambiar la configuración del resto de usuarios e incluso borrar usuarios.
