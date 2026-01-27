package com.samueldeguio.ezschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(HttpServletRequest request, NoHandlerFoundException ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "404");
        mav.addObject("errorTitle", "Page Not Found");
        mav.addObject("errorMessage", "Oops! The page you're looking for doesn't exist.");
        mav.addObject("errorDetails", "The requested URL " + request.getRequestURI() + " was not found on this server.");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(HttpServletRequest request, ResourceNotFoundException ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "404");
        mav.addObject("errorTitle", "Resource Not Found");
        mav.addObject("errorMessage", ex.getMessage());
        mav.addObject("errorDetails", "The resource you're looking for could not be found.");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ModelAndView handleUnauthorized(HttpServletRequest request, UnauthorizedException ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "401");
        mav.addObject("errorTitle", "Unauthorized");
        mav.addObject("errorMessage", "You need to be logged in to access this page.");
        mav.addObject("errorDetails", ex.getMessage());
        mav.setStatus(HttpStatus.UNAUTHORIZED);
        return mav;
    }

    @ExceptionHandler(ForbiddenException.class)
    public ModelAndView handleForbidden(HttpServletRequest request, ForbiddenException ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "403");
        mav.addObject("errorTitle", "Access Denied");
        mav.addObject("errorMessage", "You don't have permission to access this resource.");
        mav.addObject("errorDetails", ex.getMessage());
        mav.setStatus(HttpStatus.FORBIDDEN);
        return mav;
    }

    @ExceptionHandler(BadRequestException.class)
    public ModelAndView handleBadRequest(HttpServletRequest request, BadRequestException ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "400");
        mav.addObject("errorTitle", "Bad Request");
        mav.addObject("errorMessage", "Something went wrong with your request.");
        mav.addObject("errorDetails", ex.getMessage());
        mav.setStatus(HttpStatus.BAD_REQUEST);
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(HttpServletRequest request, Exception ex) {
        ModelAndView mav = new ModelAndView("pages/error");
        mav.addObject("errorCode", "500");
        mav.addObject("errorTitle", "Something Went Wrong");
        mav.addObject("errorMessage", "We encountered an unexpected error. Don't worry, we're on it!");
        mav.addObject("errorDetails", "Please try again later or contact support if the problem persists.");
        mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        
        // Log the exception for debugging
        ex.printStackTrace();
        
        return mav;
    }
}
