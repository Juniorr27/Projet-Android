package com.example.projetandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projetandroid.ui.theme.ProjetAndroidTheme
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card

import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
val resultatsTest = listOf(
    Resultat(1, 3600, 100, false),
    Resultat(2, 3850, 95, false),
    Resultat(3, 4200, 80, true),
    Resultat(0, 0, 0, false)
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Utilise le thème de ton projet
            ProjetAndroidTheme {
                // Scaffold est la structure de base d'un écran moderne
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // On appelle notre composant ListeResultats
                    ListeResultats(
                        liste = resultatsTest,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProjetAndroidTheme {
        Greeting("Android")
    }
}

@Composable
fun LigneResultat(resultat: Resultat) {
    // On crée des "mémoires" pour que l'écran se mette à jour si on tape du texte
    var afficherBoiteDeDialogue by remember { mutableStateOf(false) }
    var tempsSaisi by remember { mutableStateOf(resultat.resTemps.toString()) }
    var pointsSaisis by remember { mutableStateOf(resultat.resPoints.toString()) }

    // On ajoute .clickable sur la Card pour ouvrir la boîte de dialogue
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { afficherBoiteDeDialogue = true }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Résultat ID: ${resultat.resId}")
            Text(text = "Temps: ${resultat.resTemps} secondes")
            Text(text = "Points: ${resultat.resPoints}")

            if (resultat.aSynchroniser) {
                Text(text = "⚠️ En attente de synchronisation")
            }
        }
    }

    // Si on a cliqué sur la carte, on affiche la boîte de dialogue
    if (afficherBoiteDeDialogue) {
        AlertDialog(
            onDismissRequest = { afficherBoiteDeDialogue = false },
            title = { Text("Modifier le résultat ${resultat.resId}") },
            text = {
                Column {
                    OutlinedTextField(
                        value = tempsSaisi,
                        onValueChange = { tempsSaisi = it },
                        label = { Text("Temps (secondes)") }
                    )
                    OutlinedTextField(
                        value = pointsSaisis,
                        onValueChange = { pointsSaisis = it },
                        label = { Text("Points") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    // Pour l'instant on ferme juste la fenêtre.
                    // Bientôt, on sauvegardera les données ici !
                    afficherBoiteDeDialogue = false
                }) {
                    Text("Valider")
                }
            },
            dismissButton = {
                Button(onClick = { afficherBoiteDeDialogue = false }) {
                    Text("Annuler")
                }
            }
        )
    }
}

@Composable
fun ListeResultats(liste: List<Resultat>, modifier: Modifier = Modifier) {
    // LazyColumn crée une liste qui ne charge que ce qui est visible à l'écran
    LazyColumn {
        items(liste) { unResultat ->
            // Pour chaque élément de la liste, on dessine notre fameuse carte
            LigneResultat(resultat = unResultat)
        }
    }
}