package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import domain.Driver;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.driver.DriverService;
import view.request2_1.Request2_1InitFrame;

public class SwitchRequestButtonClick implements ActionListener {

    private JComboBox<String> request;
    private IocContainer container;

    public SwitchRequestButtonClick(JComboBox<String> request, IocContainer container) {
        this.request = request;
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String requestName = (String) request.getSelectedItem();
        switch(requestName) {
            case("Задание 2 (1)"):
                try {
                    DriverService service = container.getDriverService();
			        List<Driver> drivers = service.findAll();
			        new Request2_1InitFrame(drivers, container);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}