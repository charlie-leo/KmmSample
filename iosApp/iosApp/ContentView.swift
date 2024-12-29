import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    
    @State private var list = Greeting().getList()
    
    var body: some View {
        ZStack {
            
            LazyVStack {
                ForEach(list, id: \.self) { item in
                    
                    Text(item)
                        .padding(20)
                    
                }
            }
            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
