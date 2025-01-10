import SwiftUI
import Shared

struct ContentView: View {
    
    @StateObject private var expenseViewModel = ContentViewModel(sharedViewModel: SharedExpenseViewModel())
    
    @State private var showModelSheet = false
    @State private var amount = ""
    @State private var type = ""
    @State private var description = ""
    

    var body: some View {
        NavigationView {
            VStack {
                
                Text("")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                    .padding(.bottom, 10)
                
                List(expenseViewModel.expenseList, id: \.self) { expense in
                    HStack {
                        VStack (alignment: .leading) {
                            Text(expense.description)
                                .font(.headline)
                            
                            Text(expense.type ?? "")
                                .font(.subheadline)
                                .foregroundColor(.gray)
                        }
                        Spacer()
                        Text(String(format: "$%.2f", expense.amount ?? ""))
                            .font(.headline)
                            .fontWeight(.bold)
                    }
                    .padding(.vertical, 5)
                }
                Spacer()
            }
            .padding(.horizontal, 20)
            .navigationBarTitle("Expenses", displayMode: .inline)
            .toolbar {
                Button (action: {
                    showModelSheet.toggle()
                }) {
                    
                    Image(systemName: "plus")
                        .foregroundColor(.black)
                        .padding()
//                        .background(Color.black)
//                        .clipShape(Circle())
                    
                }
            }
            .sheet(isPresented: $showModelSheet) {
                VStack {
                    Text("Add Expense")
                        .font(.title)
                        .fontWeight(.bold)
                        .padding(.bottom, 20)
                    
                    TextField("Amount", text: $amount)
                        .keyboardType(.decimalPad)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(.bottom, 10)
                    
                    TextField("Type", text: $type)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(.bottom, 10)
                    
                    TextField("Description", text: $description)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        .padding(.bottom, 10)
                    
                    Button(action : {
                        expenseViewModel.saveExpense(
                            expense: ExpenseModel(id: 0, amount: KotlinDouble(pointer: amount) , type: type, descriptions: description)
                        )
                    }) {
                        Text("Add")
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(Color.black)
                            .cornerRadius(10)
                    }
                }
                .padding()
                
            }
        }
    }
    
   
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
