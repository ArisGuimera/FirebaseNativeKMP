import SwiftUI
import composeApp

@main
struct iOSApp: App {
    init(){
        FirebaseInitKt.doInitFirebase()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

