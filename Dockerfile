# Use the Maven image as the base image
FROM maven:3.8.4-jdk-11

# Install Xvfb
RUN apt-get update && apt-get install -y xvfb

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y libnss3-dev

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y wget gnupg && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    sh -c 'echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list' && \
    apt-get update && \
    apt-get install -y google-chrome-stable

# Update package lists and install required tools (wget and unzip)
RUN apt-get update && apt-get install -y wget unzip

# Install ChromeDriver
RUN wget https://storage.googleapis.com/chrome-for-testing-public/122.0.6261.69/linux64/chromedriver-linux64.zip -P /tmp/ \
    && unzip /tmp/chromedriver-linux64.zip -d /tmp/ \
    && mv /tmp/chromedriver-linux64/chromedriver /usr/local/bin/ \
    && rm -rf /tmp/chromedriver-linux64 /tmp/chromedriver-linux64.zip \
    && chmod +x /usr/local/bin/chromedriver


# Set Chrome as the default browser
ENV BROWSER=/usr/bin/google-chrome-stable

# Set the display to use Chrome in headless mode
ENV DISPLAY=:99

# Start Xvfb when the container starts
CMD ["Xvfb", "-ac", ":99", "-screen", "0", "1280x1024x16"]

