import sys
import requests
from PyQt5.QtWidgets import QApplication, QWidget, QLabel,QLineEdit,QPushButton,QVBoxLayout
from PyQt5.QtCore import Qt

class WeaterApp(QWidget):
    def __init__(self):
        super().__init__()
        self.cityLabel = QLabel("Enter a city name: ", self)
        self.cityImput = QLineEdit(self)
        self.getWeatherButton = QPushButton("Get Weather", self)
        self.tempLabel = QLabel ("",self)
        self.emojiLabel = QLabel("",self)
        self.descriptionLabel = QLabel("", self)
        self.initUI()



    def initUI(self): 
        self.setWindowTitle("Weather App")

        vbox = QVBoxLayout ()
        vbox.addWidget(self.cityLabel)
        vbox.addWidget(self.cityImput)
        vbox.addWidget(self.getWeatherButton)
        vbox.addWidget(self.tempLabel)
        vbox.addWidget(self.emojiLabel)
        vbox.addWidget(self.descriptionLabel)
        self.setLayout(vbox)
        self.cityLabel.setAlignment(Qt.AlignCenter)
        self.cityImput.setAlignment(Qt.AlignCenter)
        self.tempLabel.setAlignment(Qt.AlignCenter)
        self.emojiLabel.setAlignment(Qt.AlignCenter)
        self.descriptionLabel .setAlignment(Qt.AlignCenter)

        self.cityLabel.setObjectName("cityLabel")
        self.cityImput.setObjectName("cityImput")
        self.tempLabel.setObjectName("tempLabel")
        self.getWeatherButton.setObjectName("getWeatherButton")
        self.emojiLabel.setObjectName("emojiLabel")
        self.descriptionLabel.setObjectName("descriptionLabel")

        self.setStyleSheet("""

            QLabel, QPushButton{
            font-family: Times New Roman;
                           }
            QLabel#cityLabel{
                font-size:20px;
                font-style:italic;}
            QLineEdit#cityImput{
                font-size: 15px;}
            QPushButon#getWeatherButton{
                font-size: 30px; 
                font-weight: bold;}
            QLabel#tempLabel{
                font-size: 50px;}
            QLabel#emojiLabel{
                font-size: 100px;
                font-family: Apple Color Emoji; }
            QLabel#descriptionLabel{
                font-size: 60px;}
                           
        """)
        self.getWeatherButton.clicked.connect(self.getWeather)




    def getWeather(self):
        apiKey = "181c49c1155ba93cc176091dba99ac92"
        city = self.cityImput.text()
        url = f"https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}"
        try:
            response = requests.get(url)
            response.raise_for_status()
            data = response.json()
            if data["cod"] == 200:
                self.displayWeather(data)

        except requests.exceptions.HTTPError as httpError: 
            match response.status_code: 
                case 400:
                    self.displayError("bad requests \nPlease check input")
                case 401:
                    self.displayError("Unauthorized API Error")
                case 403:
                    self.displayError("Forbidden")
                case 404:
                    self.displayError("Not found")
                case 500:
                    self.displayError("internal server error try again later")
                case 502:
                    self.displayError("bad gateway ")
                case 503:
                    self.displayError("service unavailble")
                case 504:
                    self.displayError("timeout gateway ")
                case _ : 
                    self.displayError (f"http error occured { httpError}")
        except requests.exceptions.ConnectionError:
            self.displayError ("check wifi - conection error") 
        except requests.exceptions.Timeout:
            self.displayError ("request timed pit") 
 
        except requests.exceptions.TooManyRedirects:
            self.displayError ("too many redirects - check url") 
 
        except requests.exceptions.RequestException as reqError:
            self.displayError (f"request error {reqError}") 
 
      



    def displayError(self, message):
        self.tempLabel.setText(message)
        self.tempLabel.setStyleSheet("font-size: 20px;")
        self.emojiLabel.clear()
        self.descriptionLabel.clear()



    def displayWeather(self, data):
        self.tempLabel.setStyleSheet("font-size: 50px;")
        temperatureK = data["main"]["temp"]
        temperatureF = (temperatureK *9/5) - 459.67
        self.tempLabel.setText(f"{temperatureF:.0f} °F")
        wDescription = data["weather"][0]["description"]
        self.descriptionLabel.setText(wDescription)
        weatherID = data["weather"][0]["id"]
        self.emojiLabel.setText(self.weatherEMOJI(weatherID))
        
    @staticmethod
    def weatherEMOJI (weatherID):
        if 200<= weatherID<= 232:
            return"⛈️"
        elif 300<= weatherID<= 321:
            return"⛅️"
        elif 500<= weatherID<= 531:
            return"🌧️"
        elif 600<= weatherID<= 622:
            return"🌨️"
        elif 701<= weatherID<= 741:
            return"💨"
        elif weatherID ==771:
            return"💨"
        elif weatherID ==781:
            return"🌪️"
        elif weatherID ==800:
            return"☀️"
        elif 801<= weatherID<= 804:
            return"☁️"
        else: 
            return " "
         





if __name__ =="__main__":
    app = QApplication(sys.argv)
    weatherApp = WeaterApp()
    weatherApp.show()
    sys.exit(app.exec_())