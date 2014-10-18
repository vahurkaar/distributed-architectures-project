class MainController < ApplicationController
  before_filter CASClient::Frameworks::Rails::Filter

  def index
    client = Savon.client(
        :wsdl => "http://localhost:8088/core/core.wsdl"
    )

    @response = client.call(:get_customer_state_types)
  end

  def logout
    CASClient::Frameworks::Rails::Filter.logout(self)
  end
end
