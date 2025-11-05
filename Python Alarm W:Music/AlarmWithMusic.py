

import time
import datetime
import pygame



def setAlarm (alarmTime):
    print(f"alarm set for {alarmTime}")
    sound = "/Users/mahdibandali/Desktop/CS 102 2025S/MXZI - MONTAGEM TOMADA (SPED UP) [EXTENDED].mp3"
    running = True

    while running:
        currentTime = datetime.datetime.now().strftime("%H:%M:%S")
        print(currentTime)

        if currentTime == alarmTime:
            print("wake up")
            pygame.mixer.init()
            pygame.mixer.music.load(sound)
            pygame.mixer.music.play()

            while pygame.mixer.music.get_busy():
                time.sleep(1)
            running = False
        time.sleep(1)

if __name__ == "__main__":
    alarmTime = input("enter the alarm time (HH-MM-SS):" )
    setAlarm(alarmTime)

    