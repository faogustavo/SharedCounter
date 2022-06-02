import SwiftUI
import shared

struct CounterView: View {
    @ObservedObject
    var counterModel = CounterModel()
    
	var body: some View {
        VStack {
            Text("Current value")
            Text("\(counterModel.state)")
                .font(.system(size: 28))
                .bold()
                .padding()
            HStack {
                Button("Decrement") { counterModel.dec() }
                Button("Increment") { counterModel.inc() }
            }
            HStack {
                Button("Delayed decrement") { counterModel.decWithDelay() }
                Button("Delayed increment") { counterModel.incWithDelay() }
            }
        }
            .onAppear { counterModel.activate() }
            .onDisappear { counterModel.deactivate() }
	}
}

class CounterModel : ObservableObject {
    private let DELAY = Int64(1500)
    private var counterViewModel: CounterCallbackViewModel? = nil
    private var cancellers: [Canceller] = []
    
    @Published
    var state = 0
    
    func inc() {
        counterViewModel?.inc(delayMillis: 0)
    }
    
    func incWithDelay() {
        counterViewModel?.inc(delayMillis: DELAY)
    }
    
    func dec() {
        counterViewModel?.dec(delayMillis: 0)
    }
    
    func decWithDelay() {
        counterViewModel?.dec(delayMillis: DELAY)
    }
    
    func activate() {
        counterViewModel = CounterCallbackViewModel()
        cancellers.append(
            counterViewModel!.state.subscribe(
                onEach: { [weak self] count in self?.state = count.intValue },
                onComplete: {},
                onThrow: { _ in }
            )
        )
    }
    
    func deactivate() {
        cancellers.forEach { $0.cancel() }
    }
    
    deinit {
        counterViewModel?.clear()
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		CounterView()
	}
}
