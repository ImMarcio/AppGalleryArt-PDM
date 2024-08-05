import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appgaleryart.ui.theme.AppGaleryArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGaleryArtTheme {
                ScaffoldLayout()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldLayout() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Home", "Create", "Settings")
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "  Top Bar  by Marc",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                },
                actions = {

                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
        floatingActionButton = {

            Example(onClick = { /* do something */ }, Color.Black)

        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            val icon = when (item) {
                                "Home" -> Icons.Filled.Home
                                "Create" -> Icons.Filled.Email
                                "Settings" -> Icons.Filled.Settings
                                else -> Icons.Filled.Home
                            }
                            Icon(icon, contentDescription = item, modifier = Modifier.size(24.dp))
                        },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },

        ) { innerPadding ->
        // Integrate CardMinimalExample here
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp) // Add padding to prevent content from touching edges
        ) {
            PartialBottomSheet()
            CardMinimalExample()
            Spacer(modifier = Modifier.height(16.dp)) // Add spacing if needed
            FilledButtonExample(onClick = { /*TODO*/ }, buttonColor = Color.Red)
          

        }

    }
}

@Composable
fun ScrollContent(modifier: Modifier) {
    // Example content to display below the top bar and above the FAB
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp) // Add padding to prevent content from touching edges
    ) {
        Text("Main")
        Spacer(modifier = Modifier.height(16.dp)) // Add spacing if needed
        // Add more content here
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PartialBottomSheet() {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { showBottomSheet = true }
        ) {
            Text("Veja nossa messagem!")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet = false }
            ) {
                Text(
                    "Melhor app feito!",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
@Composable
fun FilledButtonExample(onClick: () -> Unit, buttonColor: Color) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text("Botão task")
    }
}

@Composable
fun Example(onClick: () -> Unit, buttonColor: Color) {
    FloatingActionButton(
        onClick = { onClick() },
        contentColor = buttonColor
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Floating action button.")
    }
}

@Composable
fun AssistChipExample() {
    AssistChip(
        onClick = { Log.d("Assist chip", "hello world") },
        label = { Text("Assist chip") },
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}
@Composable
fun CardMinimalExample() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), // Add padding and fill max width
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Optional elevation
    ) {
        Text(
            text = "In a semper urna, vitae fermentum nulla. Sed ultricies ullamcorper odio sit amet sollicitudin. Suspendisse ante risus, pretium ut neque sed, dignissim blandit magna. Aliquam sem lacus, tempor non odio eu, ultrices convallis odio. Donec dapibus ligula vitae aliquam volutpat. Fusce semper erat in mi interdum faucibus. Nullam faucibus erat urna, id varius sem vehicula eu. Quisque nec odio suscipit, scelerisque ante sed, aliquam nibh. Suspendisse lobortis viverra leo congue luctus. Nulla euismod auctor justo ut sagittis. Nulla euismod odio eu consequat molestie. Fusce ac tempus purus, in pretium leo. Ut facilisis metus quis ligula tempus iaculis.",
            modifier = Modifier.padding(16.dp) // Padding inside the card
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TopAppBarExamplePreview() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    AppGaleryArtTheme {
        ScaffoldLayout()
    }


}
