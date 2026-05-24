# 💧 Recordatorio de Hidratación

## 📱 Descripción del proyecto

Aplicación Android desarrollada en **Android Studio con Kotlin y Jetpack Compose**, diseñada para implementar tareas en segundo plano mediante **WorkManager**.

Este proyecto automatiza recordatorios de hidratación utilizando un **Periodic WorkRequest**, permitiendo ejecutar un **Worker** que muestra notificaciones automáticas incluso cuando la aplicación se encuentra cerrada.

La aplicación cuenta con una interfaz moderna, visualmente atractiva y enfocada en brindar una experiencia amigable al usuario.

---

## ✨ Características principales

✅ Implementación de **WorkManager**

✅ Uso de **Periodic WorkRequest** (mínimo permitido por Android: 15 minutos)

✅ Ejecución de tareas en segundo plano

✅ Implementación de un **Worker personalizado**

✅ Notificaciones automáticas incluso con la app cerrada

✅ Solicitud de permisos para notificaciones

✅ Interfaz moderna desarrollada con **Jetpack Compose**

✅ Diseño visual personalizado con temática de hidratación

✅ Notificación expandible con imagen personalizada

---

## 🛠️ Tecnologías utilizadas

- **Kotlin**
- **Android Studio**
- **Jetpack Compose**
- **WorkManager**
- **NotificationCompat**
- **Material Design 3**

---

## 📲 Funcionamiento

La aplicación permite al usuario activar un recordatorio automático mediante el botón:

### 💧 ACTIVAR RECORDATORIO 💧

Al activarlo:

- Se programa un **Periodic WorkRequest**
- Android ejecuta la tarea automáticamente en segundo plano
- Un **Worker** genera una notificación con el mensaje:

> **💙 ¡Es hora de beber agua! 💙**

- La notificación aparece incluso si la aplicación se encuentra cerrada

---

## 🎓 Proyecto académico

**Materia:** Programación de Dispositivos Móviles  
**Unidad:** Unidad 7 — WorkManager  
**Alumno:** Jazmín Bustillos Segundo  
**Boleta:** 2020601483

---

## 📸 Evidencia

Proyecto funcional ejecutado en dispositivo físico Android.

Incluye:

- Activación del recordatorio
- Ejecución de tarea en segundo plano
- Notificación automática funcional

---

## 👩‍💻 Autor

**Jazmín Bustillos Segundo** 💙
