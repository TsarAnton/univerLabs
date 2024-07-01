package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import domain.Break;
import domain.Car;
import domain.Car_copyGetAll;
import domain.Role;
import domain.User;
import ioc.ContainerException;
import ioc.IocContainer;
import service.ServiceException;
import service.break1.BreakService;
import service.car.CarService;
import service.car_copy.Car_copyService;
import service.role.RoleService;
import service.user.UserService;
import view.break1.BreaksListFrame;
import view.car.CarsListFrame;
import view.car_copy.Car_copysListFrame;
import view.role.RolesListFrame;
import view.user.UsersListFrame;

public class SwitchTableButtonClick implements ActionListener {

    private JComboBox<String> table;
    private IocContainer container;

    public SwitchTableButtonClick(JComboBox<String> table, IocContainer container) {
        this.table = table;
        this.container = container;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String tableName = (String) table.getSelectedItem();
        switch(tableName) {
            case("Поломки"):
                try {
                    BreakService service = container.getBreakService();
			        List<Break> breaks = service.findAll();
			        BreaksListFrame breaksListFrame = new BreaksListFrame(container);
			        breaksListFrame.setBreaks(breaks);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case("Пользователи"):
                try {
                    UserService service = container.getUserService();
			        List<User> users = service.findAll();
			        UsersListFrame usersListFrame = new UsersListFrame(container);
			        usersListFrame.setUsers(users);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case("Роли"):
                try {
                    RoleService service = container.getRoleService();
			        List<Role> roles = service.findAll();
			        RolesListFrame rolesListFrame = new RolesListFrame(container);
			        rolesListFrame.setRoles(roles);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case("Машины"):
                try {
                    CarService service = container.getCarService();
			        List<Car> cars = service.findAll();
			        CarsListFrame carsListFrame = new CarsListFrame(container);
			        carsListFrame.setCars(cars);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case("Единицы машин"):
                try {
                    Car_copyService service = container.getCar_copyService();
			        List<Car_copyGetAll> car_copys = service.findAll();
			        Car_copysListFrame car_copysListFrame = new Car_copysListFrame(container);
			        car_copysListFrame.setCar_copys(car_copys);
                } catch (ContainerException | ServiceException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ошибка запуска приложения", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}


