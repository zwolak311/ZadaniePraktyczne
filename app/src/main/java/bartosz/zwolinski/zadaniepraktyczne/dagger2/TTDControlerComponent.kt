package zwolinski.bartosz.zadaniepraktyczne.dagger2

import bartosz.zwolinski.zadaniepraktyczne.database.TTDDatabaseController
import dagger.Component

@Component(modules = [TTDDatabaseControllerModule::class])
interface TTDControllerComponent {

    fun getTTdController(): TTDDatabaseController
}