package events

import com.adeo.kviewmodel.BaseSharedViewModel
import events.models.AdminEventsAction
import events.models.AdminEventsEvent
import events.models.AdminEventsViewState

class AdminEventsViewModel: BaseSharedViewModel<AdminEventsViewState,AdminEventsAction,AdminEventsEvent>(
    initialState = AdminEventsViewState()
) {
    override fun obtainEvent(viewEvent: AdminEventsEvent) {
        TODO("Not yet implemented")
    }
}