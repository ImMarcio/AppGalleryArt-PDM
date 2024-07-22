package com.example.appgaleryart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgaleryart.ui.theme.AppGaleryArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGaleryArtTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Display(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class Pictures(
    val imageId: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun Display(modifier: Modifier) {

    val pictures = listOf(
        Pictures(R.drawable.vangogh,"Noite estrelada ", "Vincent Van Gogh", "1889"),
        Pictures(R.drawable.monaliza,"Mona Lisa ", "Leornando Da Vinci", "1506"),
        Pictures(R.drawable.ondas,"A Grande Onda sobre Kanagawa ", "Hokusai", "1830"),
    )
    var currentId by remember {mutableIntStateOf(0) }
    val currentImage = pictures[currentId]

   Column(
       modifier = Modifier
           .fillMaxSize()
           .padding(20.dp)){
       Image(
           painter = painterResource(id = currentImage.imageId),
           contentDescription = currentImage.artist,
           modifier = Modifier
               .size(300.dp)
               .background(Color.White)
               .padding(10.dp)
       )
       Text(
           text = currentImage.title,
           fontSize = 25.sp
       )
       Text(
           text = currentImage.artist,
           fontSize = 25.sp
       )

       Row (modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceEvenly){

           Button(onClick = {
               currentId = if (currentId > 0) currentId - 1 else pictures.size - 1
           }) {
               Text(text = "Previous Art")
           }
           Button(onClick = {
               currentId = if (currentId < pictures.size - 1) currentId + 1 else 0
           }) {
               Text(text = "Next Art")
           }
       }
   }


}




@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    AppGaleryArtTheme {
        Display(Modifier.padding(10.dp))
    }
}