# Load the Rails application.
require File.expand_path('../application', __FILE__)

# Initialize the Rails application.
Rails.application.initialize!

CASClient::Frameworks::Rails::Filter.configure(
    :cas_base_url  => "http://localhost:8443/cas/",
    :enable_single_sign_out => true,
    :service_url => "http://localhost:3000/"
)