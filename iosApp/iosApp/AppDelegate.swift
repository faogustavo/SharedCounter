//
//  AppDelegate.swift
//  iosApp
//
//  Created by Gustavo Fão Valvassori on 31/05/22.
//  Copyright © 2022 orgName. All rights reserved.
//
import UIKit
import shared

class AppDelegate : NSObject, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        CounterSDK.shared.initialize()
        return true
    }
}
