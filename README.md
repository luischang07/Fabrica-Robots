## Control de Concurrencia

La planta ensambladora de autos “NISSON” está construida con N (8-15) líneas de producción que trabajan durante 20 horas los 365 días del año. Cada línea de producción tiene 6 estaciones de trabajo:

1. Chasis y cableado (20 segundos).
2. Motor-transmisión (6 y 4 segundos).
3. Carrocería (10 segundos).
4. Interiores (5 segundos)
5. Llantas (5 segundos)
6. Pruebas. (10 segundos)

La planta ensambladora para realizar el trabajo de manera automática, cuenta con los siguientes robots que atienden las estaciones:

- 5 Robots para la estación 1.
- 4 Robots para la instalación del motor y 2 para la colocación de la transmisión. El robot que instala el motor termina su trabajo hasta que el robot que instala la transmisión entra en acción.
- 3 Robots en cada una de las estaciones 3 y 4.
- 2 Robots para la estación 5.
- 5 Robots para la estación 6.

Se desea que elabore un programa java que haga uso de hilos para simular la N líneas de producción de la planta ensambladora. El programa debe mostrar el número de automóvil que se está ensamblando e ir indicando la estación donde se encuentre. La producción de automóviles se detendrá cuando haya alcanzado los 1000 autos.

**Nuevos requisitos.**

Los robots utilizados en cada estación tienen un número de serie que debe de ser mostrado cuando este trabajando. Por política de seguridad los robots tienen que trabajar lo mas equitativamente.
La interfaz debe mostrar los autos y robots que están trabajando en cada estación.
