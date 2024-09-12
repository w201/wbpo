package one.codium.wbpo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import one.codium.wbpo.R
import one.codium.wbpo.core.entity.ItemMenu
import one.codium.wbpo.core.entity.ThemeMode
import one.codium.wbpo.entity.NavigationItem
import one.codium.wbpo.navigation.MovieNavigation
import one.codium.wbpo.navigation.route.MovieListRoute
import one.codium.wbpo.navigation.route.SettingsRoute
import one.codium.wbpo.ui.theme.WBPOTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<MainActivityViewModel>()
            val themeMode = remember {
                mutableStateOf(viewModel.uiState.value.themeMode)
            }
            val isDarkMode = (themeMode.value == ThemeMode.DARK) || isSystemInDarkTheme()
            WBPOTheme(isDarkMode) {
                val navController = rememberNavController()


                var canPop by remember {
                    mutableStateOf(false)
                }
                var actionMenu by remember {
                    mutableStateOf<List<ItemMenu>?>(null)
                }
                DisposableEffect(navController) {
                    val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
                        canPop = controller.previousBackStackEntry != null
                        actionMenu = emptyList()
                    }
                    navController.addOnDestinationChangedListener(listener)
                    onDispose {
                        navController.removeOnDestinationChangedListener(listener)
                    }
                }
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                var selectedNavItem by remember {
                    mutableStateOf(NavigationItem.MOVIES)
                }
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            viewModel.drawerMenu.forEach {
                                NavigationItemView(
                                    it,
                                    selectedNavItem,
                                    navController,
                                    scope,
                                    drawerState
                                ) {
                                    selectedNavItem = it
                                }
                            }
                        }
                    },
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = { Text(stringResource(id = R.string.app_name)) },
                                navigationIcon = {
                                    if (canPop) {
                                        IconButton(onClick = { navController.navigateUp() }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "back"
                                            )
                                        }
                                    } else {
                                        IconButton(onClick = {
                                            scope.launch {
                                                drawerState.apply {
                                                    if (isClosed) open() else close()
                                                }
                                            }
                                        }) {
                                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
                                        }
                                    }
                                },
                                actions = { actionMenu?.forEach { it.icon.invoke() } }
                            )
                        },

                        ) { innerPadding ->
                        Surface(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            MovieNavigation(navController, { actionMenu = it }) {
                                themeMode.value = it
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationItemView(
    navigationItem: NavigationItem,
    selectedNavItem: NavigationItem,
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState,
    onSelectedItem: (NavigationItem) -> Unit
) {
    NavigationDrawerItem(
        { Text(text = stringResource(navigationItem.label)) },
        selectedNavItem == navigationItem,
        {
            navController.navigate(navigationItem.route) { popUpTo(0) { inclusive = true } }
            scope.launch {
                drawerState.close()
            }
            onSelectedItem.invoke(navigationItem)
        }
    )
}
