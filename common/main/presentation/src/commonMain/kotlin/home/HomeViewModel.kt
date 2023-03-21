package home

import com.adeo.kviewmodel.BaseSharedViewModel
import home.models.HomeAction
import home.models.HomeEvent
import home.models.HomeViewState

class HomeViewModel : BaseSharedViewModel<HomeViewState, HomeAction, HomeEvent>(
    initialState = HomeViewState(
        username = "Erlink Halah",
        avatarUrl = "https://i.pinimg.com/736x/04/6e/91/046e91f77483a277a434bac9ae4885e2--ui-framework-user-experience-design.jpg",
        status = "Morning"
    )
) {
    override fun obtainEvent(viewEvent: HomeEvent) {
        when(viewEvent){
            HomeEvent.UserProfileClicked -> showUserProfile()
        }
    }

    private fun showUserProfile(){
        viewAction = HomeAction.ShowUserProfile
    }
}