//
//  ContentViewModel.swift
//  iosApp
//
//  Created by charles raj on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import Shared


class ContentViewModel : ObservableObject {
    
    
    private let sharedViewModel : SharedExpenseViewModel
    
    @Published var expenseList : [ExpenseModel] = []
    
    init(sharedViewModel: SharedExpenseViewModel) {
        self.sharedViewModel = sharedViewModel
        expenseList = sharedViewModel.expenseList as! [ExpenseModel]
       
//        sharedViewModel.expenseList.observe { [weak self] expenses in
//            DispatchQueue.main.async {
//                self?.expenseList = expenses
//            }
//        }
        
    }
    
//    private func observeExpenseList() {
//        sharedViewModel.fetchAllExpense.
//    }
//    
//    private func saveExpense(expense : ExpenseModel){
//        sharedViewModel.saveExpense(expenseModel: expense)
//    }
    
    
}
