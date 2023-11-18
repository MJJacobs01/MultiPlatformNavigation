package za.co.jacobs.mj.multiplatformnavigation

import android.os.Bundle
import android.view.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import kotlinx.coroutines.flow.*
import za.co.jacobs.mj.multiplatformnavigation.ui.theme.MultiPlatformNavigationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiPlatformNavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavigationController(startDestination = Screens.Login)
                    val backStack = navController.backStack.collectAsState().value

                    when (backStack.last()) {
                        is Screens.Login -> {
                            Scaffold(
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = {
                                            navController.navigateTo(Screens.Register(200))
                                        }
                                    ) {
                                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                                    }
                                }
                            ) { _ ->
                                Column {
                                    Text(text = "This is the login screen")
                                    Text(text = "This is the current backstack entry ${backStack.size}")
                                }
                            }
                        }

                        is Screens.Register -> {
                            //  Then we can use the itemId in our view model
                            val itemId = (backStack.last() as Screens.Register).itemId
                            Column {
                                Text(text = "This is the register screen")
                                Text(text = "This is the current backstack entry ${backStack.size}")
                                Text(text = "The passed item id is $itemId")
                            }
                            BackHandler {
                                navController.navigateBack()
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screens {
    object Login : Screens()
    data class Register(val itemId: Int) : Screens()
}