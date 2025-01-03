import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @State private var list: [String] = []

    var body: some View {
        ZStack {
            
            LazyVStack {
                ForEach(list, id: \.self) { item in
                    
                    Text(item)
                        .padding(20)
                    
                }
                
            }.onAppear {
                Task {
                    await loadData()
                }
            }
            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
    
    func loadData() async {
        let greet = Greeting()
            do {
                try await greet.getList { list in
                    DispatchQueue.main.async {
                        self.list = list
                    }
                }
            } catch {
                print("Error loading data: \(error)")
            }
        }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
