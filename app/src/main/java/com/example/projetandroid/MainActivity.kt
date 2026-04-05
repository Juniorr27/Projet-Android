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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
            ProjetAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(resultatsTest) { resultat ->
                            LigneResultat(resultat = resultat)
                        }
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
    // Card crée une petite "carte" visuelle avec des bords arrondis
    Card(modifier = Modifier.padding(8.dp)) {
        // Column permet d'empiler les textes les uns sous les autres
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Résultat ID: ${resultat.resId}")
            Text(text = "Temps: ${resultat.resTemps} secondes")
            Text(text = "Points: ${resultat.resPoints}")

            if (resultat.aSynchroniser) {
                Text(text = "⚠️ En attente de synchronisation")
            }
        }
    }
}
@Composable
fun ListeResultats(liste: List<Resultat>) {
    // LazyColumn crée une liste qui ne charge que ce qui est visible à l'écran
    LazyColumn {
        items(liste) { unResultat ->
            // Pour chaque élément de la liste, on dessine notre fameuse carte
            LigneResultat(resultat = unResultat)
        }
    }
}