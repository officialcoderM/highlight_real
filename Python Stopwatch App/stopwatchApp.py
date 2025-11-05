import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel,QPushButton,QVBoxLayout,QHBoxLayout
from PyQt5.QtCore import QTimer, QTime, Qt

class Stopwatch(QWidget):
    def __init__(self):
        super().__init__()
        self.time = QTime(0,0,0,0)
        self.timeLabel = QLabel("00:00:00:00",self)
        self.startButton = QPushButton ("Start",self)
        self.stopButton = QPushButton ("Stop",self)

        self.resetButton = QPushButton("Restart", self)
        self.timer = QTimer(self)
        self.initUI()

    def initUI(self):
        self.setWindowTitle("Stopwatch")

        vbox = QVBoxLayout()
        vbox.addWidget(self.timeLabel)
        

        self.setLayout(vbox)
        self.timeLabel.setAlignment(Qt.AlignCenter)
        hbox = QHBoxLayout()
        hbox.addWidget(self.startButton)
        hbox.addWidget(self.resetButton)
        hbox.addWidget(self.stopButton)

        vbox.addLayout(hbox)
        
        self.setStyleSheet("""
                           QPushButton,QLabel {
                           padding: 20px;
                           font-weight: bold;
                           font-family: Calibri; }
            
                           QPushButton{font-size: 50px}
                           QLabel{
                           font-size:    120px;
                           background-color: hsl(136, 68%, 66%); 
                            border-radius: 20px;
                           }
                           """)
        self.startButton.clicked.connect(self.start)
        self.stopButton.clicked.connect(self.stop)
        self.resetButton.clicked.connect(self.reset)
        self.timer.timeout.connect(self.updateDisplay)


    def start(self):
        self.timer.start(10)
    def stop(self):
        self.timer.stop()

    def reset(self):
        if not self.timer.isActive():
  
            self.timer.stop()
            self.time = QTime (0,0,0,0)
            self.timeLabel.setText(self.formatTime(self.time ))

    def formatTime(self,time):
        hours = time.hour()
        mins =time.minute()
        secs = time.second()
        milli = time.msec()//10
        return F"{hours:02}:{mins:02}:{secs:02}.{milli:02}"
    def updateDisplay(self):
        self.time = self.time.addMSecs(10)
        self.timeLabel.setText(self.formatTime(self.time))



if __name__ =="__main__":
    app = QApplication(sys.argv)
    stopwatch = Stopwatch()
    stopwatch.show()
    sys.exit(app.exec_())
