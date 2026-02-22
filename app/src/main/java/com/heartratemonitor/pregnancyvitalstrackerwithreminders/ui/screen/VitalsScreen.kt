package com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.screen



import com.heartratemonitor.pregnancyvitalstrackerwithreminders.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.Favorite

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsEntity
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.dialog.AddVitalsDialog
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.viewmodel.VitalsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource



val PurplePrimary = Color(0xFF9B59B6)
val PurpleLight = Color(0xFFECCBFF)
val PurpleDark = Color(0xFF8E44AD)
val TextDark = Color(0xFF2C2C2C)
val pink = Color(0xFFFFC0CB)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VitalsScreen(
    viewModel: VitalsViewModel = viewModel()
) {
    val vitals by viewModel.vitalsList.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Track My Pregnancy",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleLight
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = PurplePrimary
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
        ) {
            items(vitals) { item ->
                VitalsCard(item)
            }
        }

        if (showDialog) {
            AddVitalsDialog(
                onDismiss = { showDialog = false },
                onSubmit = {
                    viewModel.addVitals(it)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun VitalItem(
    heartRate: Int,
    systolic: Int,
    diastolic: Int,
    babyKicks: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(
                text = "Vitals Summary",
                style = MaterialTheme.typography.titleMedium
            )

            Text("❤️ Heart Rate: $heartRate bpm")
            Text("🩸 Blood Pressure: $systolic / $diastolic mmHg")
            Text("👶 Baby Kicks: $babyKicks")
        }
    }
}


@Composable
fun VitalsCard(vital: VitalsEntity) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = PurpleLight
            ),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column {

                Row(
                    modifier = Modifier
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {
                        IconText(Icons.Default.Favorite, "${vital.heartRate} bpm")
                        Spacer(Modifier.height(6.dp))
                        IconText(R.drawable.scale, "${vital.weight} kg")
                    }

                    Column {
                        IconText(
                            R.drawable.heartrate,
                            "${vital.systolic}/${vital.diastolic} mmHg"
                        )
                        Spacer(Modifier.height(6.dp))
                        IconText(
                            R.drawable.childcare,
                            "${vital.babyKicks} kicks"
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PurplePrimary)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = formatDate(vital.timestamp),
                        color = Color.White,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }


@Composable
fun IconText(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            icon,
            contentDescription = null,
            tint = PurpleDark,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(text, color = TextDark)
    }
}

@Composable
fun IconText(iconRes: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(text)
    }
}

fun formatDate(time: Long): String {
    val sdf = SimpleDateFormat(
        "EEE, dd MMM yyyy hh:mm a",
        Locale.getDefault()
    )
    return sdf.format(Date(time))
}

