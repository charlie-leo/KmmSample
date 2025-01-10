//
//  ContentViewModel.swift
//  iosApp
//
//  Created by charles raj on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared
import Combine
//import KMPNativeCoroutinesCombine

class ContentViewModel : ObservableObject {
    
    
    private let sharedViewModel : SharedExpenseViewModel
    
    @Published var expenseList : [ExpenseModel] = []
    
    init(sharedViewModel: SharedExpenseViewModel) {
        self.sharedViewModel = sharedViewModel
        
         loadExpenses()
    }
    
    private func loadExpenses()  {
        Task{
            do {
                try await sharedViewModel.fetchAllExpense { result in
                    self.expenseList = result
                }
            } catch {
                print("Failed to fetch expenses: \(error)")
            }
        }
    }
    
    func saveExpense(expense : ExpenseModel){
        Task {
            do {
                print("save request : \(expense)")
                try await sharedViewModel.saveExpense(expenseModel: expense){ result in
                    self.expenseList = result
                }
            } catch {
                print("Failed to load expenses: \(error)")
            }
        }
    }
}
