(ns simple-emailer.helper
  (:import [org.apache.commons.mail DefaultAuthenticator SimpleEmail MultiPartEmail EmailAttachment]))

(defn validate-session-params [params]
  (if (nil? (:host params)) (throw (Exception. ":host must be provided")))
  (if (nil? (:port params)) (throw (Exception. ":port must be provided")))
  (if (nil? (:user params)) (throw (Exception. ":user must be provided")))
  (if (nil? (:username params)) (throw (Exception. ":username must be provided"))))

(defn create-session [params]
  (validate-session-params params)
  params)

(defn add-recipients [message recipient-type recipients]
  (if (not (nil? recipients))
    (if (sequential? recipients)
      (doseq [recipient recipients]
        (case recipient-type
              :to (.addTo message recipient)
              :cc (.addCc message recipient)
              :bcc (.addBcc message recipient)))
      (add-recipients message recipient-type (list recipients)))))

(defn add-attachments [message attachments]
  (if (not (nil? attachments))
    (if (sequential? attachments)
      (doseq [attachment attachments]
        (.attach message (doto (EmailAttachment.)
                           (.setPath attachment)
                           (.setDisposition EmailAttachment/ATTACHMENT))))
      (add-attachments message (list attachments)))))

(defn validate-email-params [params]
  (if (nil? (:body params)) (throw (Exception. ":body must be provided")))
  (if (every? #(nil? (%1 params)) [:to :cc :bcc]) (throw (Exception. "At least 1 recipient must be provided (:to, :cc, or :bcc)"))))

(defn construct-message [message session]
  (doto message
    (.setHostName (:host session))
    (.setSslSmtpPort (:port session))
    (.setSSL (:ssl session))
    (.setFrom (:user session) (:username session))
    (.setAuthentication (:user session) (:password session))))

(defn message-type [details]
  (if (not (nil? (:attachments details)))
    (MultiPartEmail.)
    (SimpleEmail.)))

(defn create-message [session details]
  (validate-email-params details)
  (let [message (construct-message (message-type details) session)]
    (.setFrom message (or (:from details) (:user details)))
    (add-recipients message :to (:to details))
    (add-recipients message :cc (:cc details))
    (add-recipients message :bcc (:bcc details))
    (.setSubject message (:subject details))
    (.setMsg message (:body details))
    (add-attachments message (:attachments details))
    message))

(defn deliver-message [message]
  (.send message))
