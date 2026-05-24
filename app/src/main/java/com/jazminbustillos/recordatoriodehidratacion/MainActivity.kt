package com.jazminbustillos.recordatoriodehidratacion

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.jazminbustillos.recordatoriodehidratacion.ui.theme.RecordatorioDeHidratacionTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        solicitarPermisos()

        setContent {
            RecordatorioDeHidratacionTheme {
                PantallaRecordatorio {
                    activarRecordatorio()

                    Toast.makeText(
                        this,
                        "✅ Recordatorio activado. Cierra la app para ver la notificación 💧",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    100
                )
            }
        }
    }

    private fun activarRecordatorio() {

        val workManager = WorkManager.getInstance(this)

        workManager.cancelAllWork()

        val prueba =
            OneTimeWorkRequestBuilder<HidratacionWorker>()
                .setInitialDelay(8, TimeUnit.SECONDS)
                .build()

        workManager.enqueue(prueba)

        val periodico =
            PeriodicWorkRequestBuilder<HidratacionWorker>(
                15,
                TimeUnit.MINUTES
            ).build()

        workManager.enqueueUniquePeriodicWork(
            "recordatorio_hidratacion",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodico
        )
    }
}

@Composable
fun PantallaRecordatorio(onActivar: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFF90CAF9),
                        Color(0xFF42A5F5),
                        Color(0xFF1565C0)
                    )
                )
            )
            .padding(24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(id = R.drawable.agua_icon),
                contentDescription = "Agua",
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "💧 RECORDATORIO DE\nHIDRATACIÓN 💧",
                textAlign = TextAlign.Center,
                fontSize = 34.sp,
                lineHeight = 42.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                elevation = CardDefaults.cardElevation(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.95f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "✨ Tu bienestar comienza con pequeños hábitos ✨",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Mantener una hidratación constante ayuda a mejorar tu energía, concentración y bienestar general 💙",
                        textAlign = TextAlign.Center,
                        fontSize = 17.sp,
                        lineHeight = 26.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Activa el recordatorio automático para recibir notificaciones incluso con la aplicación cerrada 🚀",
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = Color.Gray
                    )
                }
            }

            Button(
                onClick = onActivar,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(12.dp)
            ) {
                Text(
                    text = "💧 ACTIVAR RECORDATORIO 💧",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF1565C0)
                )
            }
        }
    }
}