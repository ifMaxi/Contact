package com.maxidev.contact.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.maxidev.contact.ui.presentation.contacts.AddContactScreen
import com.maxidev.contact.ui.presentation.contacts.ContactScreen
import com.maxidev.contact.ui.presentation.contacts.ContactViewModel
import com.maxidev.contact.ui.presentation.contacts.EditContactScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestinations: Destinations = Destinations.CONTACT_SCREEN
) {
    NavHost(
        navController = navController,
        startDestination = startDestinations.route
    ) {
        composable(
            route = startDestinations.route
        ) {
            val viewmodel = hiltViewModel<ContactViewModel>()

            ContactScreen(
                viewModel = viewmodel,
                onAdd = {
                    navController.navigate(Destinations.ADD_CONTACT_SCREEN.route)
                },
                onEdit = {
                    navController.navigate(
                        Destinations.EDIT_CONTACT_SCREEN.route + "?id=${it.id}&" +
                                "name=${it.name}&" +
                                "lastName=${it.lastName}&" +
                                "phone=${it.phone}"
                    )
                }
            )
        }
        composable(route = Destinations.ADD_CONTACT_SCREEN.route) {
            val viewmodel = hiltViewModel<ContactViewModel>()

            AddContactScreen(
                viewModel = viewmodel,
                popBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = Destinations.EDIT_CONTACT_SCREEN.route + "?id={id}&name={name}&lastName={lastName}&phone={phone}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("lastName") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("phone")  {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val viewmodel = hiltViewModel<ContactViewModel>()

            val backStackId = backStackEntry.arguments!!.getLong("id")
            val backStackName = backStackEntry.arguments?.getString("name")
            val backStackLast = backStackEntry.arguments?.getString("lastName")
            val backStackPhone = backStackEntry.arguments?.getString("phone")

            EditContactScreen(
                viewModel = viewmodel ,
                popBack = {
                    navController.popBackStack()
                },
                id = backStackId,
                name = backStackName,
                lastName = backStackLast,
                phone = backStackPhone
            )
        }
    }
}