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
                            Text(expense.descriptions ?? "")
                                .font(.headline)
                            
                            Text(expense.type ?? "")
                                .font(.subheadline)
                                .foregroundColor(.gray)
                        }
                        Spacer()
                        if let amtExpense = expense.amount{
                            Text(String(Double(truncating: amtExpense)))
                                .font(.headline)
                                .fontWeight(.bold)
                        }
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
                        
                    
                        if let conAmount = Double(amount) {
                            let kotlinConAmount = KotlinDouble(value: conAmount)
                            expenseViewModel.saveExpense(
                                expense: ExpenseModel(id: 0, amount:  kotlinConAmount, type: type, descriptions: description)
                            )
                        } else {
                            // Failed to convert, handle error
                            print("Invalid number")
                        }
                        
                       
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
