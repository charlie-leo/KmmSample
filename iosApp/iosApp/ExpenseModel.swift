//
//  ExpenseModel.swift
//  iosApp
//
//  Created by charles raj on 05/01/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

class ExpenseModel: Codable {
    let id: Int
    let amount: Double
    let type: Date
    let description: String
}
