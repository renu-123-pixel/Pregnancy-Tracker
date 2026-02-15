package com.heartratemonitor.pregnancyvitalstrackerwithreminders.ui.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.heartratemonitor.pregnancyvitalstrackerwithreminders.data.local.VitalsEntity


val PurplePrimary = Color(0xFF9B59B6)
val PurpleLight = Color(0xFFECCBFF)
val PurpleDark = Color(0xFF8E44AD)
val TextDark = Color(0xFF2C2C2C)

@Composable
fun AddVitalsDialog(
    onDismiss: () -> Unit,
    onSubmit: (VitalsEntity) -> Unit
) {
    var sys by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }
    var heart by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "Add Vitals",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PurpleDark
                )

                Spacer(Modifier.height(16.dp))

                Row {
                    OutlinedTextField(
                        value = sys,
                        onValueChange = { sys = it },
                        label = { Text("Sys BP") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(10.dp))

                    OutlinedTextField(
                        value = dia,
                        onValueChange = { dia = it },
                        label = { Text("Dia BP") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = heart,
                    onValueChange = { heart = it },
                    label = { Text("Heart Rate") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = kicks,
                    onValueChange = { kicks = it },
                    label = { Text("Baby Kicks") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(22.dp))

                Button(
                    onClick = {
                        onSubmit(
                            VitalsEntity(
                                systolic = sys.toInt(),
                                diastolic = dia.toInt(),
                                heartRate = heart.toInt(),
                                weight = weight.toFloat(),
                                babyKicks = kicks.toInt()
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PurplePrimary
                    )
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
