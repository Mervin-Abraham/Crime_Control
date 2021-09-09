package com.example.crime_control

// Logs message using log_textview present in main_activity2.xml
class Logger {

    companion object {

        fun log( message : String ) {
            Facerecongnotion.setMessage(Facerecongnotion.logTextView.text.toString() + "\n" + ">> $message")
            // To scroll to the last message
            // See this SO answer -> https://stackoverflow.com/a/37806544/10878733
            while ( Facerecongnotion.logTextView.canScrollVertically(1) ) {
                Facerecongnotion.logTextView.scrollBy(0, 10);
            }
        }

    }

}