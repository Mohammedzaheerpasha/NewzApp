package uk.ac.tees.mad.e4294395.newsapp.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.e4294395.newsapp.ui.country.CountryListRoute
import uk.ac.tees.mad.e4294395.newsapp.ui.language.LanguageListRoute
import uk.ac.tees.mad.e4294395.newsapp.ui.newslist.NewsListRoute
import uk.ac.tees.mad.e4294395.newsapp.ui.newssources.NewsSourceRoute
import uk.ac.tees.mad.e4294395.newsapp.ui.search.SearchNewsRoute
import uk.ac.tees.mad.e4294395.newsapp.ui.topheadline.TopHeadlineroute
import uk.ac.tees.mad.e4294395.newsapp.utils.AppConstant.COUNTRY_ID
import uk.ac.tees.mad.e4294395.newsapp.utils.AppConstant.LANG_ID
import uk.ac.tees.mad.e4294395.newsapp.utils.AppConstant.SOURCE_ID
import me.hardi.newsapp.ui.paggination.PaginationArticleRoute

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigationBar() {

    val navHostController = rememberNavController()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navHostController, routeList)
        }
    ) { paddingValue: PaddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Route.TopHeadline.name,
            modifier = Modifier.padding(paddingValue)
        ) {
            composable(route = Route.TopHeadline.name) {
                TopHeadlineroute(
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    },
                    onPaginationClick = {
                        navHostController.navigate(Route.TopHeadlinePagination.name)
                    }
                )
            }
            composable(route = Route.TopHeadlinePagination.name) {
                PaginationArticleRoute(
                    onBackClick = {
                        navHostController.popBackStack()
                    },
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    },
                )
            }
            composable(route = Route.NewsSource.name) {
                NewsSourceRoute(
                    onSourceClick = {
                        navHostController.navigate(
                            Route.NewsList.passArgs(
                                it,
                                "{$COUNTRY_ID}",
                                "{$LANG_ID}"
                            )
                        )
                    }
                )
            }
            composable(route = Route.LanguageList.name) {
                LanguageListRoute(
                    onLanguageClick = {
                        navHostController.navigate(
                            Route.NewsList.passArgs(
                                "{$SOURCE_ID}",
                                "{$COUNTRY_ID}",
                                it
                            )
                        )

                    }
                )
            }
            composable(route = Route.CountryList.name) {
                CountryListRoute(
                    onCountryClick = {
                        navHostController.navigate(
                            Route.NewsList.passArgs(
                                "{$SOURCE_ID}",
                                it,
                                "{$LANG_ID}"
                            )
                        )
                    }
                )
            }
            composable(route = Route.SearchNews.name) {
                SearchNewsRoute(
                    onBackPress = {
                        navHostController.popBackStack()
                    },
                    onSearchNewsClick = {
                        openCustomChromeTab(context, it)
                    }
                )
            }
            composable(
                route = Route.NewsList.name,
                arguments = listOf(
                    navArgument(SOURCE_ID) { type = NavType.StringType },
                    navArgument(COUNTRY_ID) { type = NavType.StringType },
                    navArgument(LANG_ID) { type = NavType.StringType }
                )) {
                NewsListRoute(
                    onBackPress = {
                        navHostController.popBackStack()
                    },
                    onNewsClick = {
                        openCustomChromeTab(context, it)
                    }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController, routeList: List<Route>) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = routeList.any { it.name == currentDestination?.route }

    if (bottomBarDestination) {
        NavigationBar {
            routeList.forEach { item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White
                    ),
                    selected = currentDestination?.hierarchy?.any { it.route == item.name } == true,
                    onClick = {
                        navController.navigate(item.name) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            this@navigate.launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = null)
                    },
                    label = {
                        Text(text = stringResource(id = item.rsId))
                    }
                )
            }
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}