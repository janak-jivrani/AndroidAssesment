package com.zw.composetemplate.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.zw.composetemplate.R
import com.zw.composetemplate.databinding.ActivityDashboardBinding
import com.zw.composetemplate.presentation.viewmodels.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    private val viewModel: DashboardViewModel by viewModels()
    lateinit var activityDashboardBinding: ActivityDashboardBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        activityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)

        // Keep the splash screen visible for this Activity.
        splashScreen.setKeepOnScreenCondition { viewModel.initialSataLoad.value == false }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_home)
        /*if (viewModel.shardPref.isLogined) {
            graph.setStartDestination(R.id.channels_fragment)
            if (viewModel.shardPref.userRole == Constant.USER_ROLE_NORMAL || viewModel.shardPref.userRole == Constant.USER_ROLE_PREMIUM) {
                FirebaseUtils.scheduleMessageSyncWorker(this)
            }
        } else {
            graph.setStartDestination(R.id.login_fragment)
        }*/
        graph.setStartDestination(R.id.post_list_fragment)
        navController = navHostFragment.navController
        val bundle = if (intent.extras != null) {
            intent.extras!!
        } else {
            Bundle()
        }
        bundle.putString("fromScreen","splash")
        navController.setGraph(graph, bundle)


        // Set up an OnPreDrawListener to the root view.
//        val content: View = findViewById(android.R.id.content)
//        content.viewTreeObserver.addOnPreDrawListener(
//            object : ViewTreeObserver.OnPreDrawListener {
//                override fun onPreDraw(): Boolean {
//                    // Check whether the initial data is ready.
//                    return if (viewModel.initialSataLoad.value == true) {
//                        // The content is ready. Start drawing.
//                        content.viewTreeObserver.removeOnPreDrawListener(this)
//                        true
//                    } else {
//                        // The content isn't ready. Suspend.
//                        false
//                    }
//                }
//            }
//        )
        viewModel.setLoaded(true)

//
//        // Add a callback that's called when the splash screen is animating to the
//        // app content.
//        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
//            val splashScreenView = splashScreenViewProvider.view
//            // Create your custom animation.
//            val slideUp = android.animation.ObjectAnimator.ofFloat(
//                splashScreenView,
//                View.TRANSLATION_Y,
//                0f,
//                -splashScreenView.height.toFloat()
//            )
//            slideUp.interpolator = AnticipateInterpolator()
//            slideUp.duration = 200L
//
//            // Call SplashScreenView.remove at the end of your custom animation.
//            slideUp.doOnEnd { splashScreenViewProvider.remove() }
//
//            // Run your animation.
//            slideUp.start()
//
//
//            // Get the duration of the animated vector drawable.
//            val animationDuration = splashScreenView.iconAnimationDuration
//            // Get the start time of the animation.
//            val animationStart = splashScreenView.iconAnimationStart
//            // Calculate the remaining duration of the animation.
//            val remainingDuration = if (animationDuration != null && animationStart != null) {
//                (animationDuration - Duration.between(animationStart, Instant.now()))
//                    .toMillis()
//                    .coerceAtLeast(0L)
//            } else {
//                0L
//            }
//
//        }
    }
}
